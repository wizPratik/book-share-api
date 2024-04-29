import { Injectable } from '@nestjs/common';
import { CreateUserRequestDto } from './dto/create-user.request.dto';
import { User } from './entities/user.model';
import { RedisService } from 'src/redis/redis.service';
import { EntityPrefix } from 'src/data/entity.prefix';
import { UUID } from 'crypto';
import { UserResponseDto } from './dto/user.response.dto';
import { UpdateUserRequestDto } from './dto/update-user.request.dto';
import { SimpleResponse } from 'src/data/dto/SimpleResponse';

@Injectable()
export class UsersService {
  constructor(private readonly redisService: RedisService) {}

  async findAll() {
    const userStrings = await this.redisService.findAll(EntityPrefix.USER);
    if (userStrings.length === 0) {
      return SimpleResponse.withMessage(404, 'Users not found');
    }
    const u: User[] = userStrings.map((user) => JSON.parse(user));
    const users: UserResponseDto[] = u.map((user) => new UserResponseDto(user));
    return SimpleResponse.withData(200, users);
  }

  async findOne(id: UUID) {
    const userString = await this.redisService.findById(EntityPrefix.USER, id);
    console.log(userString);
    if (userString == null) {
      return SimpleResponse.withMessage(404, 'User not found');
    }
    return SimpleResponse.withData(
      200,
      new UserResponseDto(JSON.parse(userString)),
    );
  }

  async create(createUserReq: CreateUserRequestDto) {
    const newUser = User.fromCreateRequest(createUserReq);
    await this.redisService.save(
      EntityPrefix.USER,
      newUser.id,
      JSON.stringify(newUser),
    );
    return SimpleResponse.withData(201, new UserResponseDto(newUser));
  }

  async update(id: UUID, updateUserReq: UpdateUserRequestDto) {
    const currentUser = await this.#get(id);
    if (currentUser == null) {
      return SimpleResponse.withMessage(404, 'User not found');
    }
    const newUser = User.fromUpdateRequest(currentUser, updateUserReq);
    await this.redisService.update(
      EntityPrefix.USER,
      newUser.id,
      JSON.stringify(newUser),
    );
    return SimpleResponse.withData(201, new UserResponseDto(newUser));
  }

  async remove(id: UUID) {
    const currentUser = await this.#get(id);
    if (currentUser == null) {
      return SimpleResponse.withMessage(404, 'User not found');
    }
    await this.redisService.remove(EntityPrefix.USER, id);
    return SimpleResponse.withMessage(200, 'User deleted');
  }

  async #get(id: UUID): Promise<null | User> {
    const userString = await this.redisService.findById(EntityPrefix.USER, id);
    if (userString == null) {
      return null;
    }
    return JSON.parse(userString);
  }
}

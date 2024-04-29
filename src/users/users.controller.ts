import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  ParseUUIDPipe,
  Post,
  Put,
  ValidationPipe,
} from '@nestjs/common';
import { UsersService } from './users.service';
import { CreateUserRequestDto } from './dto/create-user.request.dto';
import { UpdateUserRequestDto } from './dto/update-user.request.dto';
import { UUID } from 'crypto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  async findAll() {
    return await this.usersService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id', ParseUUIDPipe) id: UUID) {
    return await this.usersService.findOne(id);
  }

  @Post()
  async create(@Body(ValidationPipe) createUserReq: CreateUserRequestDto) {
    return await this.usersService.create(createUserReq);
  }

  @Put(':id')
  async update(
    @Param('id', ParseUUIDPipe) id: UUID,
    @Body(ValidationPipe) updateUserReq: UpdateUserRequestDto,
  ) {
    return await this.usersService.update(id, updateUserReq);
  }

  @Delete(':id')
  async remove(@Param('id', ParseUUIDPipe) id: UUID) {
    return await this.usersService.remove(id);
  }
}

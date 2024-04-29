import { UUID, randomUUID } from 'crypto';
import { CreateUserRequestDto } from '../dto/create-user.request.dto';
import { UpdateUserRequestDto } from '../dto/update-user.request.dto';

export class User {
  id: UUID;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  location: string;
  createdAt: number;

  constructor(
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    location: string,
  ) {
    this.id = randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.location = location;
    this.createdAt = Date.now();
  }

  static fromCreateRequest(createUserReq: CreateUserRequestDto) {
    const { firstName, lastName, email, password, location } = createUserReq;
    return new User(firstName, lastName, email, password, location);
  }

  static fromUpdateRequest(
    currentUser: User,
    updateUserReq: UpdateUserRequestDto,
  ) {
    const { firstName, lastName, email, location } = updateUserReq;
    if (firstName != null) {
      currentUser.firstName = firstName;
    }
    if (lastName != null) {
      currentUser.lastName = lastName;
    }
    if (email != null) {
      currentUser.email = email;
    }
    if (location != null) {
      currentUser.location = location;
    }
    return currentUser;
  }
}

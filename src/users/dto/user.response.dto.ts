import { IsEmail, IsNotEmpty, IsString } from 'class-validator';
import { User } from '../entities/user.model';

export class UserResponseDto {
  @IsString()
  @IsNotEmpty()
  firstName: string;

  @IsString()
  @IsNotEmpty()
  lastName: string;

  @IsEmail()
  @IsNotEmpty()
  email: string;

  @IsNotEmpty()
  location: string;

  constructor(user: User) {
    const { firstName, lastName, email, location } = user;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.location = location;
  }
}

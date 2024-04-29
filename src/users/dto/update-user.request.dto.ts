import { IsString, IsEmail, IsOptional } from 'class-validator';

export class UpdateUserRequestDto {
  @IsOptional()
  @IsString()
  firstName?: string;

  @IsOptional()
  @IsString()
  lastName?: string;

  @IsOptional()
  @IsEmail()
  email?: string;

  @IsOptional()
  password?: string;

  @IsOptional()
  location?: string;
}

import { Module } from '@nestjs/common';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';
import { DataModule } from 'src/data/data.module';
import { RedisModule } from 'src/redis/redis.module';

@Module({
  controllers: [UsersController],
  providers: [UsersService],
  imports: [DataModule, RedisModule],
})
export class UsersModule {}

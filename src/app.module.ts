import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { DataModule } from './data/data.module';
import { RedisModule } from './redis/redis.module';

@Module({
  imports: [UsersModule, DataModule, RedisModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}

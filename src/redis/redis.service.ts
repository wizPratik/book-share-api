import { Inject, Injectable, OnModuleDestroy } from '@nestjs/common';
import { RedisRepository } from './redis.repository';
import { Redis } from 'ioredis';
import { REDIS_CLIENT } from './redis.client.types';

@Injectable()
export class RedisService implements OnModuleDestroy, RedisRepository {
  constructor(@Inject(REDIS_CLIENT) private readonly redisClient: Redis) {}

  onModuleDestroy(): void {
    this.redisClient.disconnect();
  }

  async findAll(prefix: string): Promise<any[]> {
    const allKeys = await this.redisClient.keys(`${prefix}:*`);
    console.log(allKeys);
    if (allKeys.length === 0) {
      return [];
    }
    return await this.redisClient.mget(allKeys);
  }

  async findById(prefix: string, key: string): Promise<any> {
    return await this.redisClient.get(`${prefix}:${key}`);
  }

  async save(prefix: string, key: string, value: string): Promise<any> {
    await this.redisClient.set(`${prefix}:${key}`, value);
  }

  async update(prefix: string, key: string, value: string): Promise<any> {
    await this.redisClient.set(`${prefix}:${key}`, value);
  }

  async remove(prefix: string, key: string): Promise<void> {
    await this.redisClient.del(`${prefix}:${key}`);
  }
}

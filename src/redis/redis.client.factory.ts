import { FactoryProvider } from '@nestjs/common';
import { Redis } from 'ioredis';
import { REDIS_HOST, REDIS_PORT } from 'src/config';
import { REDIS_CLIENT } from './redis.client.types';

export const redisClientFactory: FactoryProvider<Redis> = {
  provide: REDIS_CLIENT,
  useFactory: () => {
    const redisInstance = new Redis({ host: REDIS_HOST, port: REDIS_PORT });

    redisInstance.on('error', (e) => {
      throw new Error(`Redis connection failed: ${e}`);
    });
    return redisInstance;
  },
  inject: [],
};

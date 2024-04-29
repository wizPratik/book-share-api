// App Properties
export const APP_PORT: number = parseInt(process.env.APP_PORT ?? '3000');
export const APP_ENV: string = process.env.NODE_ENV ?? 'development';

// Redis
export const REDIS_HOST: string = process.env.REDIS_HOST ?? 'localhost';
export const REDIS_PORT: number = parseInt(process.env.REDIS_PORT ?? '6379');

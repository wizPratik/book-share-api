export interface RedisRepository {
  findAll(prefix: string): Promise<any[]>;
  findById(prefix: string, key: string): Promise<any>;
  save(prefix: string, key: string, value: string): Promise<any>;
  update(prefix: string, key: string, value: string): Promise<any>;
  remove(prefix: string, key: string): Promise<void>;
}

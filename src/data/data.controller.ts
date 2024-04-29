import { Controller, Get } from '@nestjs/common';
import { DataService } from './data.service';

@Controller('data')
export class DataController {
  constructor(private readonly dataService: DataService) {}

  @Get('/cities')
  fetchAllCities() {
    return this.dataService.fetchAllCities();
  }

  @Get('/states')
  fetchAllStates() {
    return this.dataService.fetchAllStates();
  }
}

import { Injectable } from '@nestjs/common';
import { LocationProvider } from './location.provider';

@Injectable()
export class DataService {
  fetchAllCities() {
    return LocationProvider.CITIES;
  }

  fetchAllStates() {
    return LocationProvider.STATES;
  }
}

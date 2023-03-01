export * from './healthCheckController.service';
import { HealthCheckControllerService } from './healthCheckController.service';
export * from './jwtController.service';
import { JwtControllerService } from './jwtController.service';
export * from './shopController.service';
import { ShopControllerService } from './shopController.service';
export const APIS = [HealthCheckControllerService, JwtControllerService, ShopControllerService];

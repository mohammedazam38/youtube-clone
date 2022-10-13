import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-solml3pe.us.auth0.com',
            redirectUrl:"http://localhost:4200",
            clientId: 'IcIqPdTkDgXsCwgveTRZd7qEbHecvg0o',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            secureRoutes:['http://localhost:8080/'],
            customParamsAuthRequest:{
 audience: 'http:localhost:8080'
            }
           
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}

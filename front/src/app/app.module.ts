import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatIconModule} from "@angular/material/icon";
import { HeaderNoAuthComponent } from './component/header-no-auth/header-no-auth.component';
import { RegisterComponent } from './pages/register/register.component';
import { TopicsComponent } from './pages/topics/topics.component';
import { ListTopicComponent } from './component/list-topic/list-topic.component';
import { HeaderComponent } from './component/header/header.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {AuthInterceptor} from "./interceptors/auth.interceptors";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MeComponent } from './pages/me/me.component';
import { SubscribeListTopicComponent } from './component/subscribe-list-topic/subscribe-list-topic.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, HeaderNoAuthComponent, RegisterComponent, TopicsComponent, ListTopicComponent, HeaderComponent, MeComponent, SubscribeListTopicComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    ReactiveFormsModule,
    MatCardModule,
    HttpClientModule,
    MatIconModule,
    FormsModule,
    MatToolbarModule,
    MatSnackBarModule,
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }], // add interceptor
  bootstrap: [AppComponent],
})
export class AppModule {}

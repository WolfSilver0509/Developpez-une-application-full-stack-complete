import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import { TopicsComponent} from "./pages/topics/topics.component";
import { MeComponent} from "./pages/me/me.component";
import { PostsComponent} from "./pages/posts/posts.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  { path: 'login',

    component: LoginComponent
  },
  { path: 'register',

  component: RegisterComponent
  },
  {
    path: 'topics',
    component: TopicsComponent
  },
  {
    path: 'me',
    component: MeComponent
  },
  {
    path: 'posts',
    component: PostsComponent
  }
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

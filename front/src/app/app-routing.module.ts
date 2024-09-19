import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import { TopicsComponent} from "./pages/topics/topics.component";
import { MeComponent} from "./pages/me/me.component";
import { PostsComponent} from "./pages/posts/posts.component";
import {CreatePostComponent} from "./component/create-post/create-post.component";
import { PostDetailComponent } from './pages/post-detail/post-detail.component';
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {AuthGuard} from "./guards/auth.guard";
import {UnAuthGuard} from "./guards/unauth.guard";

const routes: Routes = [
  {
    path: '',
    canActivate: [UnAuthGuard],
    component: HomeComponent
  },
  { path: 'login',
    canActivate: [UnAuthGuard],
    component: LoginComponent
  },
  { path: 'register',
    canActivate: [UnAuthGuard],
    component: RegisterComponent
  },
  {
    path: 'topics',
    component: TopicsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'me',
    component: MeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'posts',
    component: PostsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'create-post',
    component: CreatePostComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'post-detail/:id',
    component: PostDetailComponent,
    canActivate: [AuthGuard]
  },
  { path: '**',
    component: NotFoundComponent }
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

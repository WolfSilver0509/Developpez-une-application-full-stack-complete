// import { User } from './user.interface';

export interface Post {
  id: number;
  title: string;
  description: string;
  owner_id: number;
  owner_name?: string;
  created_at: string;
}



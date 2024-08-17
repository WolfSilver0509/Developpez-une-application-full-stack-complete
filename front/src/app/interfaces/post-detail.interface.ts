import { Comment } from './comment.interface';

export interface PostDetail {
  id: number;
  title: string;
  description: string;
  owner_id: number;
  topic_id: number;
  created_at: string;
  updated_at: string;
  comments: Comment[];
}


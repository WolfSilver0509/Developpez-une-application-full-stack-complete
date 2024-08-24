export interface Comment {
  id: number;
  message: string;
  owner_id: number;
  author: string;
  post_id: number;
  created_at: string;
  updated_at: string;
}

export interface CommentResponse{
  message : string;
}

import {Topic} from "./topic.interface";

export interface TopicWithSubscriptionStatus extends Topic {
  isSubscribed?: boolean;
}

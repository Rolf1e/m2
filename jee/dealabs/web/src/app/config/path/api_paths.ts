const BASE_API_ROUTE: string = "http://localhost:8080";
const PUBLIC_ROUTE: string = BASE_API_ROUTE + "/public"

export const DEALS_ROUTE: string = PUBLIC_ROUTE + "/deals/all";
export const DEAL_ROUTE: string = PUBLIC_ROUTE + "/deals/{id}";
export const DEALS_DETAILS_ROUTE: string = PUBLIC_ROUTE + "/deals/details/{id}";
export const CREATE_DEAL_ROUTE: string = BASE_API_ROUTE + "/deals";

export const CREATE_USER_ROUTE: string = PUBLIC_ROUTE + "/users";
export const USERS_DETAILS_ROUTE: string = BASE_API_ROUTE + "/users/{id}";

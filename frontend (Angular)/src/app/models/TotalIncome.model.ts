export interface TotalIncome{
    id:number;
    category:string;
    amount:number;
    note:string
}
export type NewIncome = {
  category: string |null | undefined ;
  amount: string | number |null | undefined ;
  note: string |null | undefined ;
};
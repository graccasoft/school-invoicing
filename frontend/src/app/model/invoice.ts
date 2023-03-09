import { InvoiceItem } from "./Invoice-item"

export class Invoice{
    id!: number
    createdAt!: string
    studentId!: number
    studentName!: string
    schoolClassId!: number
    schoolClassName!: string
    totalAmount!: number
    items!: InvoiceItem

}
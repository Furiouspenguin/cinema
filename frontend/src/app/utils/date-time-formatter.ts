export function dateTimeFormatter(date: Date): string {
  let dateParams: string[] = date.toLocaleString().split(',')
  return dateParams[0] + dateParams[1] + '-' + dateParams[2] + '-'+ dateParams[3] + ' ' +
    dateParams[4] + ':' + dateParams[5]
}

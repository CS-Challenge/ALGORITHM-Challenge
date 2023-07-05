import sys

sys.setrecursionlimit(10 ** 6)


def solution(k, room_number):
    answer = []

    def find(number):
        # print(number,end=' ')
        if book.get(number):  # 방이 차있으면
            next_room = find(book[number])
            book[number] = next_room + 1
            return next_room
        else:
            book[number] = number + 1
            return number

    book = {}

    for number in room_number:
        next = find(number)
        # print(next, "종료")
        answer.append(next)

    return answer


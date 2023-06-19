import sys

board = []
for _ in range(5):
    board.append(list(map(int, input().split())))
orders = []
for _ in range(5):
    orders.append(list(map(int, input().split())))


# print(board, orders)


def check(num): # 사회자가 말한 숫자 0으로 처리
    global board
    for i in range(5):
        for j in range(5):
            if board[i][j] == num:
                board[i][j] = 0
                return


def check_line(): # 몇 빙고인지 각 라인 체크
    global board
    bingo = 0
    # 가로 빙고 체크
    for i in range(5):
        flag = False
        for j in range(5):
            if board[i][j] != 0:
                flag = True
                break
        if not flag:
            bingo += 1
            # print("가로")
    # 세로 빙고 체크
    for i in range(5):
        flag = False
        for j in range(5):
            if board[j][i] != 0:
                flag = True
                break
        if not flag:
            bingo += 1
            # print("세로")

    # 왼쪽 대각선 빙고 체크
    flag = False
    for i in range(5):
        if board[i][i] != 0:
            flag = True
            break
    if not flag:
        bingo += 1
        # print("왼대")

    # 오른쪽 대각선 빙고 체크
    flag = False
    for i in range(5):
        if board[4 - i][i] != 0:
            flag = True
            break
    if not flag:
        bingo += 1
        # print("오른대")

    # print("bingo", bingo)
    return bingo


ans = 0
for order in orders:
    for num in order:
        ans += 1
        check(num)
        # print(board)
        if check_line() >= 3:
            print(ans)
            sys.exit()

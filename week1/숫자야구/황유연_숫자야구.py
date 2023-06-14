import sys

input = sys.stdin.readline
# 입력
N = int(input())
li = []
for _ in range(N):
    num, strike, ball = map(int, input().split())
    li.append([num, strike, ball])


def combi(idx, str):  # 서로 다른 세자리 숫자 조합 생성
    nums = ['1', '2', '3', '4', '5', '6', '7', '8', '9']

    global candis
    if idx == 3:
        candis.append(str)
        return

    for i in range(9):
        if nums[i] not in str:
            combi(idx + 1, str + nums[i])


def ask(candi, num):  # 영수에게 묻기
    s = 0
    b = 0
    for i in range(3):
        if candi[i] == num[i]:
            s += 1
        elif num[i] in candi:
            b += 1
    return s, b


candis = []
combi(0, '')
for num, strike, ball in li:
    for candi in candis[:]:  # !candis의 요소를 제거하기 때문에 복사본으로 포문을 돌려야함!
        s, b = ask(candi, str(num))
        if not (s == strike and b == ball):
            candis.remove(candi)
print(len(candis))

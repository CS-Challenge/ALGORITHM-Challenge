N, MIN = map(int, input().split())
arr = []  # 지도
chicken = []  # 치킨집 좌표 저장 리스트
for i in range(N):
    li = list(map(int, input().split()))
    for j in range(len(li)):
        if li[j] == 2:
            chicken.append((i, j))
    arr.append(li)

M = len(arr[0])  # 행 길이
C = len(chicken)  # 지도상 치킨집 개숫
ans = float('inf')
combs = []


def comb(cnt, idx, marked):
    # global marked
    if cnt == MIN:
        combs.append(marked.copy())
        return
    if idx >= C:
        return
    marked[cnt] = idx
    comb(cnt + 1, idx + 1, marked)
    comb(cnt, idx + 1, marked)


def calc(marked):
    tmp = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 1:  # 이 집에서 가장 가까운 치킨집
                min_ = 100
                for k in marked:
                    x, y = chicken[k]
                    min_ = min(min_, abs(i - x) + abs(j - y))
                tmp += min_
    return tmp


if C > MIN:  # 치킨집이 MIN개 보다 많을 경우 M개를 선택해야한다.
    comb(0, 0, [0] * MIN)
    for marked in combs:
        min_ = 0
        tmp = calc(marked)
        if tmp != 0:
            ans = min(tmp, ans)
    print(ans)
else:

    print(calc([i for i in range(C)]))

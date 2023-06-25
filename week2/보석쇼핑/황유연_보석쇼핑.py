from collections import defaultdict


def solution(gems):
    ans = [0, len(gems) - 1]
    kind = len(set(gems))
    counter = {}
    for x in set(gems):
        counter[x] = 0
    s, e = 0, 0
    counter[gems[0]] = 1
    kind_now = set()
    kind_now.add(gems[0])

    while e < len(gems) and s <= e:

        # 종류의 갯수만 들고있는 방식
        if kind == len(kind_now):
            if ans[1] - ans[0] > e - s:
                ans[0] = s
                ans[1] = e
        e += 1
        if e != len(gems):
            counter[gems[e]] += 1
            kind_now.add(gems[e])
            
        # s pointer 쪽에 범위 안 이미 있는 보석일 경우 포인터를 오른쪽으로 이동
        while True: 
            if counter[gems[s]] > 1:
                x = counter[gems[s]]
                counter[gems[s]] = x - 1
                s += 1
            else:
                break

    return [ans[0] + 1, ans[1] + 1]



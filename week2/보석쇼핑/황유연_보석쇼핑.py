from collections import defaultdict

def solution(gems):
    ans = [0, len(gems) - 1]
    counter = defaultdict(int)
    kind = len(set(gems))
    for x in set(gems):
        counter[x] = 0
    s, e = 0, kind
    for x in gems[s:e]:
        counter[x] += 1
    
    if kind == 1:
        return [1, 1]
    while s < len(gems) and e < len(gems):
        if all(v != 0 for v in counter.values()):
            if ans[1] - ans[0] > e - s:
                ans[0] = s
                ans[1] = e
        e += 1
        if e == len(gems): break
        counter[gems[e]] += 1
        while True:
            if counter[gems[s]] > 1:
                s += 1
                counter[gems[s]] -= 1
            else:
                break

    return [ans[0] + 1, ans[1] + 1]

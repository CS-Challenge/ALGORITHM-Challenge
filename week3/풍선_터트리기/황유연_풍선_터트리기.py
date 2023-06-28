from collections import deque


def solution(a):
    answer = []
    ori = a.copy()
    for i in range(len(a)):
        q = deque(ori)
        cnt = 0
        while len(q) > 1:
            x = q.popleft()
            y = q.popleft()
            if cnt == i:  # 작은 풍선 터뜨리기
                print(cnt)
                if x > y:
                    q.appendleft(x)
                else:
                    q.appendleft(y)
            else:  # 큰 풍선 터뜨리기
                if x > y:
                    q.appendleft(y)
                else:
                    q.appendleft(x)
            cnt += 1
        answer.append(q.popleft())
    print(answer)
    return len(set(answer))


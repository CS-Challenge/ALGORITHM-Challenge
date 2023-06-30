from collections import deque

n, w, L = map(int, input().split())
li = list(map(int, input().split()))
q = deque(li)  # 대기 큐
bridge_q = deque([0 for _ in range(w)])  # 다리에 올라간 트럭 큐
ans = 0
while len(q) > 0:
    if len(bridge_q) == w:
        bridge_q.popleft()
    ans += 1
    next = q.popleft()
    if sum(bridge_q) + next <= L and len(bridge_q) < w: # 하중과 자리 체크
        bridge_q.append(next)
    else: # 공백을 나타내기 위해 0 넣기
        bridge_q.append(0)
        q.appendleft(next)
print(ans + len(bridge_q))

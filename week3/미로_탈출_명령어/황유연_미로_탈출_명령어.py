from collections import deque


def solution(n, m, x, y, r, c, k):
    q = deque([(x - 1, y - 1, '', 0)])
    dirs = ['d', 'l', 'r', 'u'] # 알파벳 순서대로
    dx, dy = [1, 0, 0, -1], [0, -1, 1, 0]
    while q:
        x, y, tmp, cnt = q.popleft()
        if cnt > k:
            continue
        if x == r - 1 and y == c - 1:
            if (k - cnt) % 2 == 1: # 도착했는데 홀수개 남았을 때
                return "impossible"
            if cnt == k:
                return tmp

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if cnt + abs(r - x) + abs(c - y) > k:
                break
            if 0 <= nx < n and 0 <= ny < m:
                q.append((nx, ny, tmp + dirs[i], cnt + 1))

    return "impossible"

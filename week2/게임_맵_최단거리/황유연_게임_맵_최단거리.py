from collections import deque


def solution(maps):
    n = len(maps)
    m = len(maps[0])

    def bfs(y, x):
        q = deque()
        q.append((y, x, 1)) # y좌표, x좌표, 거리
        visited = [[0] * m for _ in range(n)] # 방문 표시
        visited[y][x] = 1
        dy, dx = [0, 0, 1, -1], [1, -1, 0, 0]

        while q:
            y, x, d = q.popleft()
            if y == n - 1 and x == m - 1:
                return d
            for i in range(4):
                ny = y + dy[i]
                nx = x + dx[i]
                if 0 <= ny < n and 0 <= nx < m and not visited[ny][nx] and maps[ny][nx] == 1: # 범위 내, 방문x, 장애물없는지 체크
                    visited[ny][nx] = 1
                    q.append((ny, nx, d + 1))
        return -1

    return bfs(0, 0)


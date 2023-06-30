from collections import deque

# bfs 풀이
# 방문 처리가 필요하지 않아 따로 맵을 만들 필요가 없다.
def solution(n, m, x, y, r, c, k):
    q = deque([(x, y, '', 0)])
    dirs = ['d', 'l', 'r', 'u']  # 알파벳 순서대로
    dx, dy = [1, 0, 0, -1], [0, -1, 1, 0]
    while q:
        x, y, path, cnt = q.popleft()
        if x == r and y == c:  # 도착
            # 도착했는데 홀수개 남았을 때 다른곳에 들렸다가 재방문 해야하는데 홀수번이면 이 자리로 다시 되돌아 올 수 없다.
            if (k - cnt) % 2 == 1:
                return "impossible"
            if cnt == k:
                return path

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if not (0 < nx <= n and 0 < ny <= m): continue  # 범위 체크
            if cnt + 1 + abs(r - nx) + abs(c - ny) > k:  # 현재 위치까지 오는데 사용한 횟수 + 현재 위치로부터 종점까지 남은 거리가 주어진 횟수보다 크다면
                continue
            q.append((nx, ny, path + dirs[i], cnt + 1))
            print(q)
            break

    return "impossible"

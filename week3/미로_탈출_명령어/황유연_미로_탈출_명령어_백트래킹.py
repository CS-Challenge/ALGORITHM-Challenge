# 백트래킹 풀이

def dfs(n, m, x, y, r, c, k, cnt, path):
    global answer
    dirs = [(1, 0, 'd'), (0, -1, 'l'), (0, 1, 'r'), (-1, 0, 'u')]  # x, y, d
    if answer != "":
        return
    if cnt > k:
        return
    if x == r and y == c:
        if cnt == k:
            # print(path)
            answer = path
            return
        if (k - cnt) % 2 == 1:
            return
    for dx, dy, dir in dirs:
        nx, ny = x + dx, y + dy
        if 0 >= nx or nx > n or 0 >= ny or ny > m: continue
        if cnt + 1 + abs(r - nx) + abs(c - ny) > k: continue
        dfs(n, m, nx, ny, r, c, k, cnt + 1, path + dir)

    return


def solution(n, m, x, y, r, c, k):
    global answer
    answer = ""
    dfs(n, m, x, y, r, c, k, 0, '')
    return answer if answer != "" else "impossible"


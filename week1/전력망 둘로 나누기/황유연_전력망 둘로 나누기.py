from collections import defaultdict

def solution(n, wires):
    def disconnect(a, b): # 연결 끊기
        tree[a].remove(b)
        tree[b].remove(a)

    def dfs(i, visited, cnt): # 그룹화
        visited[i] = cnt
        for k in tree[i]:
            if visited[k] == 0:
                dfs(k, visited, cnt)

    def group_size(): # 그룹 사이즈 차이 반환
        visited = [0] * (n + 1)
        cnt = 0
        for i in range(1, n + 1):
            if visited[i] == 0:
                cnt += 1
                dfs(i, visited, cnt)
        cnt = visited.count(1)
        return abs(n - cnt - cnt)

    tree = defaultdict(list)
    for a, b in wires:
        tree[a].append(b)
        tree[b].append(a)

    min_ = 100
    for k, v in tree.items():
        for child in v:
            disconnect(k, child) 
            min_ = min(group_size(), min_) 
            tree[child].append(k)
            tree[k].append(child)
    return min_

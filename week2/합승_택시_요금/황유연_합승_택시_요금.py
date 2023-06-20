import heapq


def dijkstra(node, adj, costs):
    q = []
    costs[node] = 0
    heapq.heappush(q, [0, node])
    while q:
        fare, node = heapq.heappop(q)
        if fare > costs[node]:
            continue
        for next_cost, next_node in adj[node]:
            if costs[next_node] > next_cost + fare:
                costs[next_node] = next_cost + fare
                heapq.heappush(q, (next_cost + fare, next_node))


def solution(n, s, a, b, fares):  # 지점 갯수, 시작, A, B, 요금
    adj = [[] for _ in range(n + 1)]
    for x, y, c in fares:
        adj[x].append([c, y])  # 비용, 지점 순
        adj[y].append([c, x])  # 비용, 지점 순

    cost1 = [float('inf') for _ in range(n + 1)]
    dijkstra(s, adj, cost1)  # s->a, s->b를 구할 수 있음
    sa = cost1[a]
    sb = cost1[b]

    cost2 = [float('inf') for _ in range(n + 1)]
    dijkstra(a, adj, cost2)  # a에서 모든 지점으로

    cost3 = [float('inf') for _ in range(n + 1)]
    dijkstra(b, adj, cost3)  # b에서 모든 지점으로

    ans = [sa + sb]

    for i in range(1, n + 1): # 각 지점을 경유함
        tmp = cost1[i] + cost2[i] + cost3[i]
        ans.append(tmp)
    return min(ans)


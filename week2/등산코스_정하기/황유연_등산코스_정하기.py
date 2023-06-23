import heapq


def dijkstra(start, adj, n, gates, summits):
    q = []
    heapq.heappush(q, [0, start])
    costs = [float('inf') for _ in range(n + 1)]
    while q:
        cost, node = heapq.heappop(q)
        if costs[node] < cost:
            continue
        for next_cost, next_node in adj[node]:
            if max(cost, next_cost) < costs[next_node]:
                costs[next_node] = max(cost, next_cost)
                if (next_node not in gates) and (next_node not in summits):
                    heapq.heappush(q, [costs[next_node], next_node])
    return costs


def solution(n, paths, gates, summits):
    adj = [[] for _ in range(n + 1)]
    for i, j, w in paths:
        adj[i].append([w, j])
        adj[j].append([w, i])
    min_cost = float('inf')
    min_summit = 0
    for summit in summits:
        cost = dijkstra(summit, adj, n, gates, summits)
        for gate in gates:
            if min_cost > cost[gate]:
                min_cost = cost[gate]
                min_summit = summit
            if min_cost == cost[gate]:
                min_summit = min(summit, min_summit)
    return [min_summit, min_cost]


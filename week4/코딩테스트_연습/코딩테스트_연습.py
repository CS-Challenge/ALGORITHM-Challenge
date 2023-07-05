def solution(alp, cop, problems):
    max_alp = alp
    max_cop = cop

    # 최대 알고력, 코딩력 값 찾기 - dp의 목표값
    for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems: 
        max_alp = max(max_alp, alp_req)
        max_cop = max(max_cop, cop_req)
    
    dp = [[float('inf')] * (max_cop + 1) for _ in range(max_alp + 1)]
    dp[alp][cop] = 0 # dp 시작점

    for i in range(alp, max_alp + 1):
        for j in range(cop, max_cop + 1):
            if i + 1 <= max_alp: 
                dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
            if j + 1 <= max_cop:
                dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)

            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if i >= alp_req and j >= cop_req: # 문제를 풀 수 있는 알고력, 코딩력 만족 시
                    next_alp, next_cop = min(max_alp, i + alp_rwd), min(max_cop, j + cop_rwd) # 한쪽만 최대값을 넘어갈 경우
                    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[i][j] + cost) # 순수하게 1시간씩 공부한 비용, cost를 들여 알고력/코딩력을 얻은 비용

    return dp[max_alp][max_cop]



def time_to_sec(time):
    h, m, s = map(int, time.split(':'))
    return h * 3600 + m * 60 + s


def sec_to_time(start_time_to_sec):
    h = start_time_to_sec // 3600
    start_time_to_sec %= 3600
    m = start_time_to_sec // 60
    s = start_time_to_sec % 60
    return f"{h:02d}:{m:02d}:{s:02d}"


def solution(play_time, adv_time, logs):
    play_time = time_to_sec(play_time)  # 상영 시간
    adv_time = time_to_sec(adv_time)  # 광고 시간
    dp = [0 for _ in range(play_time + 1)]

    for log in logs:
        start_time, end_time = log.split('-')
        start_time = time_to_sec(start_time)
        end_time = time_to_sec(end_time)
        dp[start_time] += 1
        dp[end_time] -= 1
    for i in range(1, play_time):
        dp[i] += dp[i - 1]
    for i in range(1, play_time):
        dp[i] += dp[i - 1]
    max_view = 0
    max_start_time = 0

    for pos in range(adv_time - 1, play_time):
        if pos >= adv_time:
            if max_view < dp[pos] - dp[pos - adv_time]:
                max_view = dp[pos] - dp[pos - adv_time]
                max_start_time = pos - adv_time + 1
        else:
            if max_start_time < dp[pos]:
                max_view = dp[pos]
                max_start_time = pos - adv_time + 1

    return sec_to_time(max_start_time)



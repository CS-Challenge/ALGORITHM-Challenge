
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
    answer = ''
    if play_time == adv_time:
        return "00:00:00"
    play_time_to_sec = time_to_sec(play_time)  # 상영 시간
    adv_time_to_sec = time_to_sec(adv_time)  # 광고 시간
    # print(play_time_to_sec)
    logs_to_sec = []
    for log in logs:
        start_time, end_time = log.split('-')
        start_time_to_sec = time_to_sec(start_time)
        end_time_to_sec = time_to_sec(end_time)
        logs_to_sec.append([start_time_to_sec, end_time_to_sec])
    logs_to_sec.sort(key=lambda x: (x[1], x[0]))
    # print(logs_to_sec)
    ori_s = 0
    ori_e = 0
    terms = []
    tmp = 0
    acc_time = 0
    # 겹치는 구간 구하기
    for i in range(len(logs_to_sec)):
        s, e = logs_to_sec[i]
        if s <= ori_e:
            tmp += 1
            if ori_e - s > adv_time_to_sec:
                acc_time += adv_time
            else:
                acc_time += ori_e - s
            if i == len(logs_to_sec) - 1:
                terms.append([acc_time, ori_s, ori_e, tmp])
        else:
            if tmp != 0:
                terms.append([acc_time, ori_s, ori_e, tmp])
            tmp = 1
            acc_time = 0
            ori_e = e
            if e - adv_time_to_sec > 0:  # 끝점 기준 광고 시간만큼 구간을 정해둔다.
                ori_s = max(e - adv_time_to_sec, s)
            else:
                ori_s = s
    terms.sort(key=lambda x: (-x[0], x[1]))
    if terms:
        answer = sec_to_time(terms[0][1])
    # print(terms)

    return answer


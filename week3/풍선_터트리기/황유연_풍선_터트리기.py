def solution(a):
    if len(a) == 1:
        return 1
    answer = 2
    left = [a[0]]
    right = [a[-1]]
    tmp_left = a[0]
    tmp_right = a[-1]

    for i in range(1, len(a)):
        if tmp_left < a[i]:
            left.append(tmp_left)
        else:
            tmp_left = a[i]
            left.append(a[i])
        if tmp_right < a[len(a) - 1 - i]:
            right.append(tmp_right)
        else:
            tmp_right = a[len(a) - 1 - i]
            right.append(a[len(a) - 1 - i])
    right.reverse()
    for i in range(1, len(a) - 1):
        if a[i] <= left[i] or a[i] <= right[i]:
            answer += 1
    return answer


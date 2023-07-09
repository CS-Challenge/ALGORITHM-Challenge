def solution(commands):
    answer = []
    sheet = [[0] * 51 for _ in range(51)]
    value_dict = {}
    for i in range(51):
        for j in range(51):
            sheet[i][j] = i * 51 + j
    for command in commands:
        li = command.split()

        if li[0] == "UPDATE" and len(li) == 4:
            _, r, c, value = li
            idx = sheet[int(r)][int(c)]
            value_dict[idx] = value
        if li[0] == "UPDATE" and len(li) == 3:
            _, v1, v2 = li
            for k, v in value_dict.items():
                if v == v1:
                    idx = sheet[k // 51][k % 51]
                    value_dict[idx] = v2
        if li[0] == "MERGE":
            _, r1, c1, r2, c2 = li
            idx1 = sheet[int(r1)][int(c1)]
            idx2 = int(r2) * 51 + int(c2)
            idx2_p = sheet[int(r2)][int(c2)]
            if r1 == r2 and c1 == c2:
                continue

            for i in range(51):
                for j in range(51):
                    if sheet[i][j] == idx2_p:
                        sheet[i][j] = idx1
            sheet[int(r2)][int(c2)] = idx1

            if idx1 not in value_dict.keys() and idx2 in value_dict.keys():  # 1에 값이 없으면
                value_dict[idx1] = value_dict[idx2]
                del value_dict[idx2]
            if idx2 in value_dict.keys():
                del value_dict[idx2]

        if li[0] == "UNMERGE":
            _, r, c = li
            idx = sheet[int(r)][int(c)]
            tmp = value_dict[idx]
            for i in range(51):
                for j in range(51):
                    if sheet[i][j] == idx:
                        sheet[i][j] = i * 51 + j
            if idx in value_dict.keys():
                del value_dict[idx]
            value_dict[int(r) * 51 + int(c)] = tmp
        if li[0] == "PRINT":
            _, r, c = li
            k = sheet[int(r)][int(c)]
            if k in value_dict.keys():
                answer.append(value_dict[k])
            else:
                answer.append("EMPTY")

    return answer


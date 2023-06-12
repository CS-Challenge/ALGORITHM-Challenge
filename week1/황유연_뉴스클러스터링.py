from collections import defaultdict
import math


def solution(str1, str2):
    answer = 0
    CON = 65536
    intersection = 0
    dict1 = defaultdict(int)
    dict2 = defaultdict(int)
    # 대문자화
    str1 = str1.upper()
    str2 = str2.upper()
    # 리스트로 변환
    str1 = list(str1)
    str2 = list(str2)

    # 두개씩 묶기
    for i in range(len(str1) - 1):
        dict1[str1[i] + str1[i + 1]] += 1
    for i in range(len(str2) - 1):
        dict2[str2[i] + str2[i + 1]] += 1
    
    
    # 정제
    to_delete = []
    for k in dict1.keys():
        if not ('A' <= k[0] <= 'Z' and 'A' <= k[1] <= 'Z'):
            to_delete.append(k)
    
    for k in to_delete:
        del dict1[k]
    to_delete = []

    for k in dict2.keys():
        if not ('A' <= k[0] <= 'Z' and 'A' <= k[1] <= 'Z'):
            to_delete.append(k)

    for k in to_delete:
        del dict2[k]

    # 교집합 갯수 찾기
    for k, v in dict1.items():
        if k in dict2.keys():
            intersection += min(v, dict2[k])
    union = (sum(dict1.values()) + sum(dict2.values()) - intersection)
    
    # 합집합 0 예외처리
    if union == 0:
        return CON
    answer = intersection / union
    return math.trunc(answer * CON)


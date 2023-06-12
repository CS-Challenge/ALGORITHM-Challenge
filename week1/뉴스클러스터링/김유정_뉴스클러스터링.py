import re
import math
from collections import defaultdict

# 자카드 유사도 = 교집합 크기 / 합집합 크기
def solution(str1, str2):
    answer = 0
    regex = "[^a-zA-Z]"
    str1Dict = defaultdict(int)
    str2Dict = defaultdict(int)
    str1 = re.sub(regex, "", str1)
    str2 = re.sub(regex, "", str2)
    
    print(str1)
    print(str2)
    print([str1[i: i + 2] for i in range(0, len(str1) - 1)])
    print([str2[i: i + 2] for i in range(0, len(str2) - 1)])
    
    for word in [str1[i: i + 2] for i in range(0, len(str1) - 1)]:
        str1Dict[word.lower()] += 1
    for word in [str2[i: i + 2] for i in range(0, len(str2) - 1)]:
        str2Dict[word.lower()] += 1
        
    keyset = set(str1Dict.keys())
    keyset.update(str2Dict.keys())
    intersectionCnt = 0
    unionCnt = 0
    for key in keyset:
        intersectionCnt += min(str1Dict[key], str2Dict[key])
        unionCnt += max(str1Dict[key], str2Dict[key])
        print(f"key: {key}, 교집합: {intersectionCnt}, 합집합: {unionCnt}")
    
    print(f"결과: {intersectionCnt / unionCnt}")
    print(f"결과: {65536 * (intersectionCnt / unionCnt)}")
    
    return math.trunc(65536 * (intersectionCnt / unionCnt))
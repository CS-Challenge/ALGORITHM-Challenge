def search(num):
    len_ = len(num)

    # 기본 경우(base case) 처리
    if len_ == 1 or '1' not in num or '0' not in num:  # 한자리 숫자, 모든 숫자가 0이거나 1일 때
        return True

    mid = len_ // 2

    if num[mid] == '0':
        return False

    # 왼쪽 절반, 오른쪽 절반
    return search(num[:mid]) and search(num[mid + 1:])


def to_binary(num):
    b = ''
    while num > 0:
        b += str(num % 2)
        num = num // 2
    return b[::-1]


def solution(numbers):
    bin_numbers = [to_binary(x) for x in numbers]
    bin_list = [2 ** x - 1 for x in range(50)]
    answer = []

    for number in bin_numbers:
        length = len(number)

        # 패딩을 위한 2의 배수
        for num in bin_list:
            if num >= length:
                number = '0' * (num - length) + number
                break

        # search 함수를 사용하여 조건 확인 후 결과를 answer 리스트에 추가
        if search(number):
            answer.append(1)
        else:
            answer.append(0)

    return answer


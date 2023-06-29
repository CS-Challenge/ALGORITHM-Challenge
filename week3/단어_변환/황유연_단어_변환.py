def solution(begin, target, words):
    answer = 100
    visited = [False] * len(words)

    def dfs(word, L): # 검사 할 단어, depth level
        nonlocal answer
        if word == target:
            answer = min(answer, L)
            return

        for j in range(len(words)): # 단어 순서대로 검사
            cnt = 0
            next_word = words[j]
            if not visited[j]: 
                for i in range(len(next_word)): # 같은 글자수 갯수 검사
                    if next_word[i] == word[i]:
                        cnt += 1

                if cnt + 1 == len(word): # 글자 갯수 하나 차이이면 검사
                    visited[j] = True
                    dfs(words[j], L + 1)
                    visited[j] = False

    dfs(begin, 0)

    return answer if answer < 100 else 0

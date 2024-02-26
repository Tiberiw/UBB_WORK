def cuvinte_duplicate(words):
    wordsMap = {1}  # a set
    for word in words.split(" "):
        if word in wordsMap:
            return word
        else:
            wordsMap.add(word)
    return ""


print(cuvinte_duplicate("ana are ana are mere rosii ana"))

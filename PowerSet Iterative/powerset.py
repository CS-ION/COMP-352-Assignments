stack = [[]]
queue = [[]]
array = [1,2,3]

for element in array:
    n = len(stack)
    for i in range(n):
        value = queue.pop(0)
        stack.append(value+[element])
        queue.append(value)
        queue.append(value+[element])

print(stack)
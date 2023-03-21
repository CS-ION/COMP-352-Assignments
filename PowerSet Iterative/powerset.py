queue = [[]]
array = [1,2,3] #the sample array

for element in array:
    n = len(queue)
    for i in range(n):
        value = queue.pop(0)
        queue.append(value)
        queue.append(value+[element])

print(queue)

import numpy as np


def score_game(game_core_v1):
    """Запускаем игру 1000 раз, чтобы узнать, как быстро игра угадывает число"""
    count_ls = []
    #np.random.seed(1)  # фиксируем RANDOM SEED, чтобы ваш эксперимент был воспроизводим!
    random_array = np.random.randint(1, 101, size=1000)
    for number in random_array:
        count_ls.append(game_core_v1(number))
    score = int(np.mean(count_ls))
    print(f"Ваш алгоритм угадывает число в среднем за {score} попыток")
    return (score)


def game_core_v1(number):
    """Просто угадываем на random, никак не используя информацию о больше или меньше.
       Функция принимает загаданное число и возвращает число попыток"""
    count = 0
    while True:
        count += 1
        predict = np.random.randint(1, 101)  # предполагаемое число
        if number == predict:
            return (count)  # выход из цикла, если угадали


def game_core_v2(number):
    """Сначала устанавливаем любое random число, а потом уменьшаем
    или увеличиваем его в зависимости от того, больше оно или меньше нужного.
       Функция принимает загаданное число и возвращает число попыток"""
    count = 0
    predict = np.random.randint(1, 100)
    while number != predict:
        count += 1
        if number > predict:
            predict += 1
        elif number < predict:
            predict -= 1
    return (count)  # выход из цикла, если угадали




def game_core_v3(number):
    #print(number)
    count = 0
    low = 1
    high = 101
    my_number = np.random.randint(low, high)
    #print(f'Set a number between 1 and 100: {my_number}')
    while number != my_number:
        count += 1
        if my_number > number: 
            #print('Your number is bigger!')
            high = my_number
            my_number = np.random.randint(low, high)
            #print(f'Set a number between 1 and 100: {my_number}')
        elif my_number < number:
            #print('Your number is smaller!')
            low = my_number + 1
            my_number = np.random.randint( low, high) 
            #print(f'Set a number between 1 and 100: {my_number}') 
    return(count)     

print(score_game(game_core_v1)) 
print(score_game(game_core_v2))
print(score_game(game_core_v3))
   
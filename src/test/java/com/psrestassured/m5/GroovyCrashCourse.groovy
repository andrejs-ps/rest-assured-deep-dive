package com.psrestassured.m5

class GroovyCrashCourse {

    static def langs = ['Java', 'JavaScript', 'C#', 'Python']
    static def nums = [10, 15, 30]

    static void main(String[] args) {

        println(langs[0])
        println(langs[-1])

        def jList = langs.stream()
                .filter( lang -> lang.startsWith('J'))
                .toList()

        println(jList)

        println(langs.findAll { lang -> lang.startsWith('J') })
        println(langs.findAll { it.startsWith('J') })

        println(nums.max {it})  // 30
        println(nums.average {it})
        println(langs.min {it.length()})    // shortest string
    }
}

# -*- coding: utf-8 -*-
__author__ = "Francesco"
__version__ = "0101 2021/11/04"

import os
from pathlib import Path

head, tail = os.path.split(__file__)
os.chdir(head)
os.chdir("../..")
repo_path = os.getcwd()
repository_name = os.path.split(repo_path)[1]

# CONSTANT
src_git_directory = "https://github.com/deMartiniFrancesco/" + repository_name + "/tree/master"
readme_path = "/doc/README.md"
src_path = repo_path + "/src/"
dir_project_name = "demartini_F_"

# README
header_md = "# " + repository_name + """

"""
last_md = """## Last

| PROJECT | README |
| :--- | ---: |
"""
projects_md = """
## Projects

| PROJECT | README |
| :--- | ---: |
"""


def search_last_update_project(src_directory: str):
    all_subdirectories = [
        src_directory + directory for directory in os.listdir(src_directory)
        if directory.startswith(dir_project_name)
    ]

    return max(all_subdirectories, key=os.path.getmtime)


def last_project_string(dir_updated: str):
    string = "| null | null |\n"
    if dir_updated != "":
        top, end = os.path.split(dir_updated)
        src = Path(dir_updated).resolve().parts
        src_name = src[len(src) - 2]
        string = "| " + \
                 "[" + end + "]" + \
                 "(" + src_git_directory + "/" + src_name + "/" + end + "/bin)" + \
                 " | " + \
                 "[ReadMe]" + \
                 "(" + src_git_directory + "/" + src_name + "/" + end + readme_path + ")" + \
                 " |" + \
                 "\n"
    return string


def projects_string(src_directory: str):
    string = ""
    for directory in os.listdir(src_directory):
        src_name = Path(src_directory).resolve().name

        if directory.startswith(dir_project_name):
            string += "| " + \
                      "[" + directory + "]" + \
                      "(" + src_git_directory + "/" + src_name + "/" + directory + "/bin)" + \
                      " | " + \
                      "[ReadMe]" + \
                      "(" + src_git_directory + "/" + src_name + "/" + directory + readme_path + ")" + \
                      " |" + \
                      "\n"
        else:
            continue
    return string


def write_readme(last_string: str, project_string: str):
    try:
        file_readme = open(repo_path + "//README.md", "w")

        file_readme.write(
            header_md +
            last_md +
            last_string +
            projects_md +
            project_string
        )
        file_readme.close()
    except IOError as error:
        print(error)
        return False
    return True


def update_md(src_directory: str, dir_updated: str):
    return write_readme(last_project_string(dir_updated), projects_string(src_directory))


boold = True
if __name__ == "__main__":
    if boold:
        print("Start")

    update_md(src_path, search_last_update_project(src_path))

    if boold:
        print("End")

FROM gitpod/workspace-full
USER gitpod
RUN sudo add-apt-repository -y ppa:neovim-ppa/stable
RUN sudo apt-get update -q && \
    sudo apt-get install -yq \
    python3-dev python3-pip python3-dev neovim
